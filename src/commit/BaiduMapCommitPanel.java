package commit;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.ui.CommitMessage;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BaiduMapCommitPanel {
    private JPanel main_panel;
    private JLabel branch_name;
    private JLabel type;
    private JLabel icafe_ids;
    private JLabel subject;
    private JLabel reason;
    private JLabel change_detail;
    private JLabel qa_name;
    private JTextField branch_name_filed;
    private JTextField type_filed;
    private JTextField subject_filed;
    private JTextField icafe_ids_filed;
    private JTextField reason_filed;
    private JTextField change_detail_filed;
    private JTextField qa_names_filed;
    private JComboBox type_combo_box;
    private JComboBox qa_name_combo_box;
    private JLabel regression;
    private JTextField regression_text_filed;
    private static final String[] qaNmaes = {
            "none",
            "suchaojia",
            "wuzewu",
            "xielijuan",
            "xuwanran",
            "wangli",
            "zhangdaike",
            "jiakang"

    };

    public JPanel getMainPanel() {
        return main_panel;
    }

    private String getChangeType() {
        BaiduChangeType type = (BaiduChangeType) type_combo_box.getSelectedItem();
        return type.label();
    }

    public void init(Project project) {
        for (BaiduChangeType type : BaiduChangeType.values()) {
            type_combo_box.addItem(type);
        }
        type_combo_box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                BaiduChangeType changeType = (BaiduChangeType) e.getItem();
                if (changeType != null) {
                    type_filed.setText(changeType.title);
                }
            }
        });

        for (String qaName : qaNmaes) {
            qa_name_combo_box.addItem(qaName);
        }

        qa_name_combo_box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String qaName = (String) e.getItem();
                if (qaName != null) {
                    qa_names_filed.setText(qaName);
                }
            }
        });

        String currentBranch = BnavCommitMessage.extractBranchName(project);
        if (currentBranch != null) {
            branch_name_filed.setText(currentBranch);
        }
    }

    BaiduMapCommitPanel() {
        super();
    }

    public String getBranchName() {
        return branch_name_filed.getText();
    }

    public String getType() {
        return type_filed.getText();
    }

    public String getIcafeIds() {
        return icafe_ids_filed.getText();
    }

    public String getSubject() {
        return subject_filed.getText();
    }

    public String getReason() {
        return reason_filed.getText();
    }

    public String getChangeDetail() {
        return change_detail_filed.getText();
    }

    public String getRegression() {
        return change_detail_filed.getText();
    }

    public String getQaNames() {
        return qa_names_filed.getText();
    }
}
