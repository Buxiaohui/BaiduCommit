package commit;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.impl.ProjectLevelVcsManagerImpl;
import git4idea.GitBranch;
import git4idea.GitLocalBranch;
import git4idea.GitRemoteBranch;
import git4idea.branch.GitBranchUtil;
import git4idea.branch.GitBranchesCollection;
import git4idea.repo.GitRepository;
import org.apache.http.util.TextUtils;
import org.zmlx.hg4idea.util.HgUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BnavCommitMessageUtils {

    public static String[] getLocalBranchNames(Project project) {
        try {
            ArrayList<String> names = new ArrayList<>();
            ProjectLevelVcsManager instance = ProjectLevelVcsManagerImpl.getInstance(project);
            if (instance.checkVcsIsActive("Git")) {
                GitRepository gitRepository = GitBranchUtil.getCurrentRepository(project);
                GitBranchesCollection gitBranchesCollection = gitRepository.getBranches();
                if (gitBranchesCollection != null) {
                    Collection<GitLocalBranch> localBranchs = gitBranchesCollection.getLocalBranches();
                    if (localBranchs != null && !localBranchs.isEmpty()) {
                        Iterator<GitLocalBranch> localBrancheIterator = localBranchs.iterator();
                        while (localBrancheIterator.hasNext()) {
                            GitLocalBranch gitLocalBranch = localBrancheIterator.next();
                            if (gitLocalBranch != null && !TextUtils.isEmpty(gitLocalBranch.getName())
                                    && !TextUtils.isEmpty(gitLocalBranch.getName().trim())) {
                                names.add(gitLocalBranch.getName().trim());
                            }
                        }
                        if (names != null && names.size() > 0) {
                            for (String name : names) {
                                System.out.println("getLocalBranchNames localBranchs:" + name);
                            }
                            return names.toArray((new String[names.size()]));
                        }
                        return null;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static String[] getRemoteBranchName(Project project) {
        ArrayList<String> names = new ArrayList<>();
        try {
            ProjectLevelVcsManager instance = ProjectLevelVcsManagerImpl.getInstance(project);
            if (instance.checkVcsIsActive("Git")) {
                GitRepository gitRepository = GitBranchUtil.getCurrentRepository(project);
                GitBranchesCollection gitBranchesCollection = gitRepository.getBranches();
                if (gitBranchesCollection != null) {
                    Collection<GitRemoteBranch> remoteBranchs = gitBranchesCollection.getRemoteBranches();
                    Collection<GitLocalBranch> localBranchs = gitBranchesCollection.getLocalBranches();
                    if (remoteBranchs != null && !remoteBranchs.isEmpty()) {
                        Iterator<GitRemoteBranch> remoteBrancheIterator = remoteBranchs.iterator();
                        while (remoteBrancheIterator.hasNext()) {
                            GitRemoteBranch gitRemoteBranch = remoteBrancheIterator.next();
                            if (gitRemoteBranch != null && !TextUtils.isEmpty(gitRemoteBranch.getNameForRemoteOperations())
                                    && !TextUtils.isEmpty(gitRemoteBranch.getNameForRemoteOperations().trim())) {
                                names.add(gitRemoteBranch.getNameForRemoteOperations().trim());
                            }
                        }
                        if (names != null && names.size() > 0) {
                            for (String name : names) {
                                System.out.println("getRemoteBranchName remoteBranchs:" + name);
                            }
                            return names.toArray((new String[names.size()]));
                        }
                        return null;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                // just for git...
                return null;
            }
        } catch (Exception e) {

        }
        return null;
    }

    private static String[] getNames(ArrayList<GitBranch> gitBranchList) {
        if (gitBranchList == null) {
            return null;
        }
        ArrayList<String> names = new ArrayList<>();
        Iterator<GitBranch> branchIterator = gitBranchList.iterator();
        while (branchIterator.hasNext()) {
            GitBranch gitBranch = branchIterator.next();
            if (gitBranch != null && !TextUtils.isEmpty(gitBranch.getName())
                    && !TextUtils.isEmpty(gitBranch.getName().trim())) {
                names.add(gitBranch.getName().trim());
            }
        }
        if (names != null && names.size() > 0) {
            return names.toArray((new String[names.size()]));
        }
        System.out.println("getAllBranchName localBranchs");
        return null;
    }

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
