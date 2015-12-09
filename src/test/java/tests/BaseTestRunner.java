package tests;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.PropertyLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static driver.WebDriverFactory.initDriver;

public class BaseTestRunner extends JUnitStories {

    protected static String browserName;
    protected static String baseUrl;
    protected static String baseLoginUrl;
    protected static String loginPartUrl;
    protected static String settingsUrl;
    protected static String userEmail;
    protected static String userPassword;
    protected static WebDriver driver;
    protected String storyPath;

    protected String setStoryPath() {
        return "";
    }

    public BaseTestRunner() {
        this.storyPath = setStoryPath();
        configuredEmbedder().embedderControls()
                .doIgnoreFailureInStories(false)
                .doIgnoreFailureInView(false)
                .doGenerateViewAfterStories(true);
    }

    @BeforeStory
    public void beforeStory() throws Exception {
        initProperties();
        driver = initDriver(browserName);
    }

    @AfterStory
    public void afterStory() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(getClass().getClassLoader()))
                .useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.HTML, Format.STATS, Format.CONSOLE))
                .useStoryControls(new StoryControls()
                                .doResetStateBeforeScenario(true)
                                .doSkipBeforeAndAfterScenarioStepsIfGivenStory(false)
                                .doResetStateBeforeStory(false)
                );
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(org.jbehave.core.io.CodeLocations.codeLocationFromPath("src/test/resources/"), "**/" + storyPath, "");
    }

    @Before
    public void clean() throws IOException {
        try {
            File dir = new File("target/jbehave");
            for (File file : dir.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        } catch (Exception ex) {
        }
    }

    protected static void initProperties() {
        browserName = System.getenv("BROWSER_NAME");
        baseUrl = PropertyLoader.loadProperty("base.url");
        baseLoginUrl = PropertyLoader.loadProperty("base.login.url");
        loginPartUrl = PropertyLoader.loadProperty("login.part.url");
        settingsUrl = PropertyLoader.loadProperty("settings.url");
        userEmail = PropertyLoader.loadProperty("user.email");
        userPassword = PropertyLoader.loadProperty("user.password");
    }

}
