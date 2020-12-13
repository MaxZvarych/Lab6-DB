package ua.lviv.iot.controller;

import java.util.List;

public interface ControllerWithDTO<T, S> {
    List<T> createDtos(Iterable<S> entities);

    T createDto(S entity);
}
