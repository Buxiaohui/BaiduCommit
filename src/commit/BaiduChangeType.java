package commit;

/**
 * From https://github.com/commitizen/conventional-commit-types
 *
 * @author Damien Arrachequesne
 */
public enum BaiduChangeType {

    NONE("", "什么也不想写"),
    FEAT("Features", "需求"),
    BUG("Bug", "修复bug"),
    DOCS("Documentation", "仅添加文案"),
    STYLE("Styles", "修改格式但不影响功能，如空格、换行等"),
    REFACTOR("Code Refactoring", "重构"),
    PERF("Performance Improvements", "优化"),
    TEST("Tests", "增加或修改测试代码"),
    BUILD("Builds", "修改编译相关，如gradle脚本"),
    REVERT("Reverts", "回滚某个提交"),
    OTHER("Others", "其他");

    public final String title;
    public final String description;

    BaiduChangeType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String label() {
        return this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.label(), this.description);
    }
}
