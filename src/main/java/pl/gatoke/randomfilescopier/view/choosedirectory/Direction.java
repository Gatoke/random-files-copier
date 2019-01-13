package pl.gatoke.randomfilescopier.view.choosedirectory;

import lombok.Getter;

@Getter
public enum Direction {

    SOURCE("Choose source folder"),
    DESTINATION("Choose destination folder");

    private final String message;

    Direction(final String message) {
        this.message = message;
    }
}
