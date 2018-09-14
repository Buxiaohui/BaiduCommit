package commit;

/**
 * From https://github.com/commitizen/conventional-commit-types
 *
 * @author Damien Arrachequesne
 */
public enum BaiduChangeType {

    NONE("", "select nothing"),
    FEAT("Features", "A new feature"),
    BUG("Bug", "A bug fix"),
    DOCS("Documentation", "Documentation only changes"),
    STYLE("Styles", "Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)"),
    REFACTOR("Code Refactoring", "A code change that neither fixes a bug nor adds a feature"),
    PERF("Performance Improvements", "A code change that improves performance"),
    TEST("Tests", "Adding missing tests or correcting existing tests"),
    BUILD("Builds", "Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)"),
    REVERT("Reverts", "Reverts a previous commit");

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
