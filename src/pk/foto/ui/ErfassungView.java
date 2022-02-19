package pk.foto.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class ErfassungView<T> extends Application {
    public boolean showView() {
        return false;
    }

    public abstract T gibNeuesObjekt();

}
