package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class FileDownloader {

    public void waitForFileDownload(int totalTimeoutInSec, String expectedFileName, String downloadPath, WebDriver driver) throws IOException {
        Files.deleteIfExists(Paths.get(downloadPath + "\\" + expectedFileName)); //если такой файл есть - удаляем
        FluentWait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(totalTimeoutInSec, TimeUnit.SECONDS)
                .pollingEvery(200, TimeUnit.MILLISECONDS);
        File fileToCheck = Paths.get(downloadPath)
                .resolve(expectedFileName)
                .toFile();
        wait.until((WebDriver wd) -> fileToCheck.exists());
    }

}
