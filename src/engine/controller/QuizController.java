package engine.controller;

import engine.model.dto.request.QuizAnswerDto;
import engine.model.dto.request.QuizCreationDto;
import engine.model.dto.response.*;
import engine.model.entity.*;
import engine.service.QuizCompletionService;
import engine.service.QuizService;
import engine.service.UserService;
import engine.util.QuizMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;
    private final QuizCompletionService quizCompletionService;
    private final UserService userService;
    private final QuizMapper quizMapper;

    public QuizController(QuizService service,
                          QuizCompletionService completedQuizService,
                          UserService userService,
                          QuizMapper quizMapper) {
        this.quizService = service;
        this.quizCompletionService = completedQuizService;
        this.userService = userService;
        this.quizMapper = quizMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable("id") int id) {
        return quizService.findById(id)
                .map(quiz -> new ResponseEntity<>(quizMapper.toDto(quiz), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<QuizPageableDto> getQuizzes(@RequestParam("page") int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        Page<Quiz> page = quizService.findAll(pageRequest);
        List<QuizDto> quizDtos = page.stream()
                .map(quizMapper::toDto)
                .toList();
        QuizPageableDto quizPageableDto = new QuizPageableDto(
                page.getTotalPages(),
                page.getTotalElements(),
                page.isLast(),
                page.isFirst(),
                page.getSort(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.getSize(),
                page.isEmpty(),
                page.getPageable(),
                quizDtos
        );
        return ResponseEntity.ok()
                .body(quizPageableDto);
    }

    @GetMapping("/completed")
    public ResponseEntity<CompletedQuizPageableDto> getCompletedQuizzes(@RequestParam("page") int pageNumber,
                                                                        @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow();
        PageRequest pageRequest = PageRequest.of(pageNumber, 10, Sort.by("completedAt").descending());
        Page<CompletedQuiz> page = quizCompletionService.findByUserId(user.getId(), pageRequest);
        List<CompletedQuizDto> sortedCompletedQuizDtos = page.stream()
                .map(completedQuiz -> new CompletedQuizDto(completedQuiz.getQuiz().getId(), completedQuiz.getCompletedAt()))
                .toList();
        CompletedQuizPageableDto completedQuizPageableDto = new CompletedQuizPageableDto(
                page.getTotalPages(),
                page.getTotalElements(),
                page.isLast(),
                page.isFirst(),
                page.isEmpty(),
                sortedCompletedQuizDtos
        );
        return ResponseEntity.ok()
                .body(completedQuizPageableDto);
    }

    @PostMapping
    public ResponseEntity<QuizDto> uploadQuiz(@Valid @RequestBody QuizCreationDto quizCreationDto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        Optional<User> userOptional = userService.findByEmail(email);
        if(userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        quizCreationDto.setUser(userOptional.get());
        return new ResponseEntity<>(
                quizMapper.toDto(quizService.uploadQuiz(quizCreationDto)),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<QuizResultDto> uploadAnswer(@PathVariable("id") int id,
                                                      @RequestBody QuizAnswerDto quizAnswer,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        if(quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz quiz = quizOptional.get();
        if(!equalLists(quiz.getAnswer(), quizAnswer.answer())) {
            return ResponseEntity.ok()
                    .body(new QuizResultDto(false, "Wrong answer! Please, try again."));
        }
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).orElseThrow();
        CompletedQuiz completedQuiz = new CompletedQuiz(user.getId(), LocalDateTime.now(), quiz);
        quiz.getCompletedQuizzes().add(completedQuiz);
        quizCompletionService.save(completedQuiz);
        return ResponseEntity.ok()
                .body(new QuizResultDto(true, "Congratulations, you're right!"));
    }

    private <T> boolean equalLists(List<T> list1, List<T> list2) {
        return list1.stream().sorted().toList()
                .equals(list2.stream().sorted().toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("id") int id,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Quiz> quizOptional = quizService.findById(id);
        if(quizOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String email = userDetails.getUsername();
        Quiz quiz = quizOptional.get();
        if(!quiz.getUser().getEmail().equals(email)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        quizService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
