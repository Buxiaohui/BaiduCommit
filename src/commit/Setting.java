package commit;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


//@State(
//        name = "BNAV_COMMIT",
//        storages = {@Storage(id = "BNAV_COMMIT_ID", file = "$APP_CONFIG$/format.xml")}
//)

public class Setting implements PersistentStateComponent<Element> {

    @Nullable
    @Override
    public Element getState() {
        return null;
    }

    @Override
    public void loadState(@NotNull Element element) {

    }

    @Override
    public void noStateLoaded() {

    }
}
