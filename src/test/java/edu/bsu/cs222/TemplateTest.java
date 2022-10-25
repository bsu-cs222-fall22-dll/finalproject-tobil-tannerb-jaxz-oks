package edu.bsu.cs222;

import org.apache.hc.core5.http.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class TemplateTest {
    @Test
    public void TemplateFormatterTest() throws IOException, ParseException {
        TemplateFormatter formatter = new TemplateFormatter();
        Assertions.assertEquals(
                "(1) Name: Drake Hotline Bling                                Example: https://i.imgflip.com/30b1gx.jpg\n" +
                "(2) Name: Two Buttons                                        Example: https://i.imgflip.com/1g8my4.jpg\n" +
                "(3) Name: Distracted Boyfriend                               Example: https://i.imgflip.com/1ur9b0.jpg\n" +
                "(4) Name: Running Away Balloon                               Example: https://i.imgflip.com/261o3j.jpg\n" +
                "(5) Name: UNO Draw 25 Cards                                  Example: https://i.imgflip.com/3lmzyx.jpg\n" +
                "(6) Name: Left Exit 12 Off Ramp                              Example: https://i.imgflip.com/22bdq6.jpg\n" +
                "(7) Name: Buff Doge vs. Cheems                               Example: https://i.imgflip.com/43a45p.png\n" +
                "(8) Name: Change My Mind                                     Example: https://i.imgflip.com/24y43o.jpg\n" +
                "(9) Name: Bernie I Am Once Again Asking For Your Support     Example: https://i.imgflip.com/3oevdk.jpg\n" +
                "(10) Name: Gru's Plan                                         Example: https://i.imgflip.com/26jxvz.jpg\n" +
                "(11) Name: Waiting Skeleton                                   Example: https://i.imgflip.com/2fm6x.jpg\n" +
                "(12) Name: Epic Handshake                                     Example: https://i.imgflip.com/28j0te.jpg\n" +
                "(13) Name: Batman Slapping Robin                              Example: https://i.imgflip.com/9ehk.jpg\n" +
                "(14) Name: Hide the Pain Harold                               Example: https://i.imgflip.com/gk5el.jpg\n" +
                "(15) Name: Expanding Brain                                    Example: https://i.imgflip.com/1jwhww.jpg\n" +
                "(16) Name: Blank Nut Button                                   Example: https://i.imgflip.com/1yxkcp.jpg\n" +
                "(17) Name: One Does Not Simply                                Example: https://i.imgflip.com/1bij.jpg\n" +
                "(18) Name: Disaster Girl                                      Example: https://i.imgflip.com/23ls.jpg\n" +
                "(19) Name: Woman Yelling At Cat                               Example: https://i.imgflip.com/345v97.jpg\n" +
                "(20) Name: Sad Pablo Escobar                                  Example: https://i.imgflip.com/1c1uej.jpg\n",
                formatter.formatTop20());
    }
}