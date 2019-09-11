package org.zhongweixian.live.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author: caoliang1918@aliyun.com
 * @date: 2017/12/11 22:06
 */
public class RandCodeImage {

    public String randCodeType = "5";
    public String randomKey;
    public String randomCode;
    public int randCodeLength = 4;
    public final int count = 200;
    public final int width = 105;
    public final int height = 35;
    public final int lineWidth = 2;

    public RandCodeImage() {
    }

    public String getRandCodeType() {
        return this.randCodeType;
    }

    public void setRandCodeType(String randCodeType) {
        this.randCodeType = randCodeType;
    }

    public String getRandomKey() {
        return this.randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getRandomCode() {
        return this.randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public int getRandCodeLength() {
        return this.randCodeLength;
    }

    public void setRandCodeLength(int randCodeLength) {
        this.randCodeLength = randCodeLength;
    }

    public RandCodeImage(String randCodeType, String randomKey, int randCodeLength) {
        this.randCodeType = randCodeType;
        this.randomKey = randomKey;
        this.randCodeLength = randCodeLength;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        BufferedImage image = new BufferedImage(105, 35, 1);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 105, 35);
        graphics.drawRect(0, 0, 104, 34);
        Random random = new Random();

        int i;
        for(int resultCode = 0; resultCode < 200; ++resultCode) {
            graphics.setColor(this.getRandColor(150, 200));
            i = random.nextInt(102) + 1;
            int y = random.nextInt(32) + 1;
            int xl = random.nextInt(2);
            int yl = random.nextInt(2);
            graphics.drawLine(i, y, i + xl, y + yl);
        }

        String var11 = this.exctractRandCode();

        for(i = 0; i < var11.length(); ++i) {
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Times New Roman", 1, 24));
            graphics.drawString(String.valueOf(var11.charAt(i)), 23 * i + 8, 26);
        }

        request.getSession().setAttribute(this.randomKey, var11);
        this.randomCode = var11;
        graphics.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private String exctractRandCode() {
        if(this.randCodeType == null) {
            return RandCodeImageEnum.NUMBER_CHAR.generateStr(this.randCodeLength);
        } else {
            switch(this.randCodeType.charAt(0)) {
                case '1':
                    return RandCodeImageEnum.NUMBER_CHAR.generateStr(this.randCodeLength);
                case '2':
                    return RandCodeImageEnum.LOWER_CHAR.generateStr(this.randCodeLength);
                case '3':
                    return RandCodeImageEnum.UPPER_CHAR.generateStr(this.randCodeLength);
                case '4':
                    return RandCodeImageEnum.LETTER_CHAR.generateStr(this.randCodeLength);
                case '5':
                    return RandCodeImageEnum.ALL_CHAR.generateStr(this.randCodeLength);
                default:
                    return RandCodeImageEnum.NUMBER_CHAR.generateStr(this.randCodeLength);
            }
        }
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
