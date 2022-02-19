module pk {

	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	requires metadata.extractor;
	requires xmpcore;
	opens pk.foto.ui to javafx.graphics;
}