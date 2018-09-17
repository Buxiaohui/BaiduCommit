package commit;

import com.intellij.openapi.project.Project;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BaiduMapCommitPanel {
    private JPanel main_panel;
    private JLabel branch_name;
    private JLabel issue_ids;
    private JLabel type;
    private JLabel subject;
    private JLabel reason;
    private JLabel change_detail;
    private JLabel qa_name;
    private JLabel regression;
    private JTextField branch_name_filed;
    private JTextField type_filed;
    private JTextField subject_filed;
    private JTextField issue_ids_filed;
    private JTextField reason_filed;
    private JTextField change_detail_filed;
    private JTextField qa_names_filed;
    private JComboBox type_combo_box;
    private JComboBox qa_name_combo_box;
    private JTextField regression_text_filed;
    private JComboBox remote_branch_combo_box;
    private JComboBox local_branch_combo_box;
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

    public void init(Project project) {
        initTypeList();
        initQaList();
        initLocalBranchList(project);
        initRemoteBranchList(project);
        initCurrentBranch(project);
    }

    public JPanel getMainPanel() {
        return main_panel;
    }

    private String getChangeType() {
        BaiduChangeType type = (BaiduChangeType) type_combo_box.getSelectedItem();
        return type.label();
    }

    private void initTypeList() {
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
    }

    private void initQaList() {
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

    }

    private void initLocalBranchList(Project project) {
        String[] strings = BnavCommitMessageUtils.getLocalBranchNames(project);
        for (String qaName : strings) {
            local_branch_combo_box.addItem(qaName);
        }
        local_branch_combo_box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name = (String) e.getItem();
                if (!TextUtils.isEmpty(name)) {
                    branch_name_filed.setText(name);
                }
            }
        });
    }

    private void initRemoteBranchList(Project project) {
        String[] strings = BnavCommitMessageUtils.getRemoteBranchName(project);
        for (String qaName : strings) {
            remote_branch_combo_box.addItem(qaName);
        }
        remote_branch_combo_box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name = (String) e.getItem();
                if (!TextUtils.isEmpty(name)) {
                    branch_name_filed.setText(name);
                }
            }
        });
    }

    private void initCurrentBranch(Project project) {
        String currentBranch = BnavCommitMessageUtils.extractBranchName(project);
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

    public String getIssueIds() {
        return issue_ids_filed.getText();
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
        return regression_text_filed.getText();
    }

    public String getQaNames() {
        return qa_names_filed.getText();
    }
}
