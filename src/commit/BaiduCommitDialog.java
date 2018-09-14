package commit;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * @author Damien Arrachequesne
 */
public class BaiduCommitDialog extends DialogWrapper {

    private final BaiduCommitPanel panel;

    BaiduCommitDialog(@Nullable Project project) {
        super(project);
        panel = new BaiduCommitPanel(this);
        setTitle("Commit");
        setOKButtonText("OK");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }

    String getCommitMessage() {
        return String.format("%s(%s): %s%s%s%s%s%s",
                getBranch(),
                getType(),
                getIds(),
                getSubject(),
                getReason(),
                getChangeDetail(),
                getQaNames());
    }

    private String getType() {
        if (isBlank(panel.getType())) {
            return "";
        }
        return String.format("%n%n%s", "[" + panel.getType() + "]");
    }

    private String getBranch() {
        if (isBlank(panel.getBranchName())) {
            return "";
        }
        return String.format("%n%n%s", "[" + panel.getBranchName() + "]");
    }

    private String getIds() {
        if (isBlank(panel.getIcafeIds())) {
            return "";
        }
        return String.format("%n%n%s", "[" + panel.getIcafeIds() + "]");
    }

    private String getSubject() {
        if (isBlank(panel.getSubject())) {
            return "";
        }
        return String.format("%n%n%s", panel.getSubject());
    }

    private String getReason() {
        if (isBlank(panel.getReason())) {
            return "";
        }
        return String.format("%n%n%s:", "Reason:" + panel.getReason());
    }

    private String getChangeDetail() {
        if (isBlank(panel.getChangeDetail())) {
            return "";
        }
        return String.format("%n%n%s", "Change Detail:" + panel.getChangeDetail());
    }

    private String getQaNames() {
        if (isBlank(panel.getQaNames())) {
            return "";
        }
        return String.format("%n%n%s", "QA:" + panel.getQaNames());
    }

    private static String breakLines(String input, int maxLineLength) {
        String[] tokens = input.split("\\s+");
        StringBuilder output = new StringBuilder(input.length());
        int lineLength = 0;
        for (int i = 0; i < tokens.length; i++) {
            String word = tokens[i];

            boolean shouldAddNewLine = lineLength + (" " + word).length() > maxLineLength;
            if (shouldAddNewLine) {
                if (i > 0) {
                    output.append(System.lineSeparator());
                }
                lineLength = 0;
            }
            boolean shouldAddSpace = i < tokens.length - 1 &&
                    (lineLength + (word + " ").length() + tokens[i + 1].length() <= maxLineLength);
            if (shouldAddSpace) {
                word += " ";
            }
            output.append(word);
            lineLength += word.length();
        }
        return output.toString();
    }
}
