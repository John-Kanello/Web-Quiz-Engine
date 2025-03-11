package engine.util;

public interface Mapper<X,Y> {
    Y toDto(X entity);
}
