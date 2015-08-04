package magic.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import magic.utility.MagicResources;

public class KeywordDefinitions {

    private static final KeywordDefinitions INSTANCE = new KeywordDefinitions();

    private final List<KeywordDefinition> keywordDefinitions;

    private KeywordDefinitions() {
        keywordDefinitions = new ArrayList<>();
    }

    public void loadKeywordDefinitions() {
        keywordDefinitions.clear();
        final String content = MagicResources.getKeywordsFileContent();
        KeywordDefinition current = null;
        try (final Scanner sc = new Scanner(content)) {
            while (sc.hasNextLine()) {
                final String line = sc.nextLine();
                if (line.startsWith("*")) {
                    current = new KeywordDefinition();
                    current.name = line.substring(1).trim();
                    keywordDefinitions.add(current);
                } else {
                    if (current.description.length() > 0) {
                        current.description = current.description + "<br>" + line.trim();
                    } else {
                        current.description = line.trim();
                    }
                }
            }
        }

    }

    public List<KeywordDefinition> getKeywordDefinitions() {
        return keywordDefinitions;
    }

    public static KeywordDefinitions getInstance() {
        return INSTANCE;
    }

    public static class KeywordDefinition {
        public String name;
        public String description = "";
    }
}
