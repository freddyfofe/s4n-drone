package com.freddyfofe.s4n.drone.service.implementation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.freddyfofe.s4n.drone.exceptions.DroneApplicationException;
import com.freddyfofe.s4n.drone.exceptions.DroneApplicationParseFileException;
import com.freddyfofe.s4n.drone.service.IFileReaderService;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileReaderServiceImplTests {

  IFileReaderService fileReaderService = FileReaderServiceImpl.getInstance();

  @Test
  @DisplayName("The files should be read and should be correct")
  public void fileShouldBeCorrect() throws URISyntaxException, DroneApplicationException {
    URL inputUrl =
        this.getClass().getClassLoader()
            .getResource("in/success");
    File file = new File(inputUrl.toURI());

    Map<String, List<String>> data = fileReaderService.readFiles(file);

    Assertions.assertEquals(1, data.size());
    Assertions.assertEquals(3, data.get("01").size());
  }

  @Test
  @DisplayName("The files should be read and should be wrong")
  public void fileShouldBeWrong() throws URISyntaxException, DroneApplicationException {
    URL inputUrl =
        this.getClass().getClassLoader()
            .getResource("in/errors");
    File file = new File(inputUrl.toURI());

    assertThrows(DroneApplicationParseFileException.class, () -> {
      fileReaderService.readFiles(file);
    });
  }

}
