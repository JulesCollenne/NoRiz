module NoRiz {
    requires javafx.controls;
    requires javafx.media;
    exports worldBuilder;
    exports utils;

    opens sounds to javafx.media;
    opens ui to javafx.graphics;

}