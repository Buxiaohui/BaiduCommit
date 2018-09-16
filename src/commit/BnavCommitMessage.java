package commit;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl;
import git4idea.GitLocalBranch;
import git4idea.branch.GitBranchUtil;
import git4idea.repo.GitRepository;
import org.zmlx.hg4idea.util.HgUtil;

public class BnavCommitMessage {
    public static String extractBranchName(Project project) {
        String branch = "";
        try {
            ProjectLevelVcsManager instance = ProjectLevelVcsManagerImpl.getInstance(project);
            if (instance.checkVcsIsActive("Git")) {
                GitRepository gitRepository = GitBranchUtil.getCurrentRepository(project);
                GitLocalBranch currentBranch = gitRepository.getCurrentBranch();

                if (currentBranch != null) {
                    // Branch name  matches Ticket Name
                    branch = currentBranch.getName().trim();
                    System.out.println("extractBranchName fullName:" + currentBranch.getFullName());
                    System.out.println("extractBranchName name:" + currentBranch.getName());
                }
            } else if (instance.checkVcsIsActive("Mercurial")) {
                branch = HgUtil.getCurrentRepository(project).getCurrentBranch().trim();
            }
        } catch (Exception e) {
            System.out.println("extractBranchName e:" + e.toString());
        }


        return branch;
    }
}
