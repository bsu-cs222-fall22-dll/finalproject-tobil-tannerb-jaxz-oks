package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TemplateTest {
    @Test
    public void TemplateFormatterTest() {
        List<Template> templateList = JSONParser.getTemplateList();
        Assertions.assertEquals(
                """
                        (1) Name: Drake Hotline Bling                                Example: https://i.imgflip.com/30b1gx.jpg
                        (2) Name: Two Buttons                                        Example: https://i.imgflip.com/1g8my4.jpg
                        (3) Name: Distracted Boyfriend                               Example: https://i.imgflip.com/1ur9b0.jpg
                        (4) Name: Running Away Balloon                               Example: https://i.imgflip.com/261o3j.jpg
                        (5) Name: UNO Draw 25 Cards                                  Example: https://i.imgflip.com/3lmzyx.jpg
                        (6) Name: Left Exit 12 Off Ramp                              Example: https://i.imgflip.com/22bdq6.jpg
                        (7) Name: Buff Doge vs. Cheems                               Example: https://i.imgflip.com/43a45p.png
                        (8) Name: Change My Mind                                     Example: https://i.imgflip.com/24y43o.jpg
                        (9) Name: Bernie I Am Once Again Asking For Your Support     Example: https://i.imgflip.com/3oevdk.jpg
                        (10) Name: Gru's Plan                                         Example: https://i.imgflip.com/26jxvz.jpg
                        (11) Name: Waiting Skeleton                                   Example: https://i.imgflip.com/2fm6x.jpg
                        (12) Name: Epic Handshake                                     Example: https://i.imgflip.com/28j0te.jpg
                        (13) Name: Batman Slapping Robin                              Example: https://i.imgflip.com/9ehk.jpg
                        (14) Name: Hide the Pain Harold                               Example: https://i.imgflip.com/gk5el.jpg
                        (15) Name: Expanding Brain                                    Example: https://i.imgflip.com/1jwhww.jpg
                        (16) Name: Blank Nut Button                                   Example: https://i.imgflip.com/1yxkcp.jpg
                        (17) Name: One Does Not Simply                                Example: https://i.imgflip.com/1bij.jpg
                        (18) Name: Disaster Girl                                      Example: https://i.imgflip.com/23ls.jpg
                        (19) Name: Woman Yelling At Cat                               Example: https://i.imgflip.com/345v97.jpg
                        (20) Name: Sad Pablo Escobar                                  Example: https://i.imgflip.com/1c1uej.jpg
                        (21) Random
                        """,
                TemplateFormatter.formatTop20(templateList));
    }
}