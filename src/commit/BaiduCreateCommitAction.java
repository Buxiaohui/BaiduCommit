package commit;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import org.jetbrains.annotations.Nullable;

/**
 * @author Damien Arrachequesne
 */
public class BaiduCreateCommitAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        final CommitMessageI commitPanel = getCommitPanel(actionEvent);
        Project project = actionEvent.getData(PlatformDataKeys.PROJECT);
        //Messages.showMessageDialog(project, "text", "print method", Messages.getInformationIcon());
        System.out.println("奥术大师大所大所");

        if (commitPanel == null)
            return;

        BaiduCommitDialog dialog = new BaiduCommitDialog(actionEvent.getProject());
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            String msg = dialog.getCommitMessage();
            commitPanel.setCommitMessage(msg);
        }
    }

    @Nullable
    private static CommitMessageI getCommitPanel(@Nullable AnActionEvent e) {
        if (e == null) {
            return null;
        }
        Refreshable data = Refreshable.PANEL_KEY.getData(e.getDataContext());
        if (data instanceof CommitMessageI) {
            return (CommitMessageI) data;
        }
        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.getDataContext());
    }
}
