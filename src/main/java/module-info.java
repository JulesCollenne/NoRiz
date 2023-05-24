module NoRiz {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    exports worldBuilder;
    exports utils;

    opens sounds to javafx.media;
    opens ui to javafx.graphics;
    exports ui to javafx.graphics;
}