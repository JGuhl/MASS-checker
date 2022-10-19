package eu.qped.java.style;

import eu.qped.java.checkers.mass.QfStyleSettings;
import eu.qped.java.checkers.style.StyleChecker;
import eu.qped.java.checkers.style.StyleFeedback;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StyleCheckerTest {

    private static final String METHOD_FAIL_FEEDBACK = "Your method name does not match the rules that are given.";
    private StyleChecker styleChecker;

    @BeforeEach
    void setup() {
        styleChecker = StyleChecker.builder().build();
    }

    @Test
    void testMethodFail() {
        styleChecker.setTargetPath("tmp/code-example-for-style-testing-fail");
        styleChecker.setQfStyleSettings(getBeginnerStyleSetting());
        styleChecker.check();
        var feedbacks = styleChecker.getStyleFeedbacks();


        assertThat(feedbacks).isNotEmpty();

        Condition<? super List<? extends StyleFeedback>> correctFeedbacks =
                new Condition<>(
                        feedbackList ->
                                feedbackList.stream().anyMatch(
                                        f ->
                                                f.getContent().contains(METHOD_FAIL_FEEDBACK)
                                )
                        , ""
                );
        assertThat(feedbacks).has(correctFeedbacks);
    }
    @Test
    void testMethodPass() {
        styleChecker.setTargetPath("tmp/code-example-for-style-testing-pass");
        styleChecker.setQfStyleSettings(getBeginnerStyleSetting());
        styleChecker.check();
        var feedbacks = styleChecker.getStyleFeedbacks();

        Condition<? super List<? extends StyleFeedback>> correctFeedbacks =
                new Condition<>(
                        feedbackList ->
                                feedbackList.stream().noneMatch(
                                        f ->
                                                f.getContent().contains(METHOD_FAIL_FEEDBACK)
                                )
                        , ""
                );
        assertThat(feedbacks).has(correctFeedbacks);
    }

    @AfterEach
    void tearDown() {
        styleChecker = null;
    }


    private static QfStyleSettings getBeginnerStyleSetting() {
        return QfStyleSettings
                .builder()
                .basisLevel("BEGINNER")
                .complexityLevel("BEGINNER")
                .namesLevel("BEGINNER")
                .classLength("-1")
                .methodLength("-1")
                .cyclomaticComplexity("-1")
                .fieldsCount("-1")
                .variableNamePattern("[a-z][a-zA-Z0-9]*")
                .methodNamePattern("[a-z][a-zA-Z0-9]*")
                .classNamePattern("[A-Z][a-zA-Z0-9_]*")
                .build();
    }


}
