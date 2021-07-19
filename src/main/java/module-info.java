module NoRiz {
    requires javafx.controls;
    requires javafx.media;

    opens sounds to javafx.media;
    opens ui to javafx.graphics;

}