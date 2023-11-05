import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorChooser extends JPanel {
    //RGB
    private JTextField redField;
    private JTextField greenField;
    private JTextField blueField;

    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JPanel colorPreview;
    private Color currentColorRGB;

    //CMYK
    private JTextField cyanField;
    private JTextField magentaField;
    private JTextField yellowField;
    private JTextField blackField;
    private JSlider cyanSlider;
    private JSlider magentaSlider;
    private JSlider yellowSlider;
    private  JSlider blackSlider;
    private Color currentColorCMYK;

    //HSV
    private JTextField hueField;
    private JTextField saturationField;
    private JTextField valueField;

    private JSlider hueSlider;
    private JSlider saturationSlider;
    private JSlider valueSlider;


    public ColorChooser() {

        Box modelBox = Box.createHorizontalBox();
        Box bigVerticalBox = Box.createVerticalBox();

        //CMYK MODEL
        Box boxCMYK = Box.createVerticalBox();

        cyanField = new JTextField("100");
        magentaField = new JTextField("100");
        yellowField = new JTextField("100");
        blackField = new JTextField("100");

        cyanSlider = new JSlider(0, 100, 100);
        magentaSlider = new JSlider(0, 100, 100);
        yellowSlider = new JSlider(0, 100, 100);
        blackSlider = new JSlider(0, 100, 50);

        Box boxCyan = Box.createVerticalBox();
        boxCyan.add(new JLabel("Cyan:"));
        boxCyan.add(Box.createVerticalStrut(15));
        boxCyan.add(cyanField);
        boxCyan.add(Box.createVerticalStrut(15));
        boxCyan.add(cyanSlider);
        boxCMYK.add(boxCyan);

        Box boxMagenta = Box.createVerticalBox();
        boxMagenta.add(new JLabel("Magenta:"));
        boxMagenta.add(Box.createVerticalStrut(15));
        boxMagenta.add(magentaField);
        boxMagenta.add(Box.createVerticalStrut(15));
        boxMagenta.add(magentaSlider);
        boxCMYK.add(boxMagenta);

        Box boxYellow = Box.createVerticalBox();
        boxYellow.add(new JLabel("Yellow:"));
        boxYellow.add(Box.createVerticalStrut(15));
        boxYellow.add(yellowField);
        boxYellow.add(Box.createVerticalStrut(15));
        boxYellow.add(yellowSlider);
        boxCMYK.add(boxYellow);

        Box boxBlack = Box.createVerticalBox();
        boxBlack.add(new JLabel("Black:"));
        boxBlack.add(Box.createVerticalStrut(15));
        boxBlack.add(blackField);
        boxBlack.add(Box.createVerticalStrut(15));
        boxBlack.add(blackSlider);
        boxCMYK.add(boxBlack);

        boxCMYK.add(Box.createVerticalStrut(15));

        modelBox.add(boxCMYK);
        modelBox.add(Box.createHorizontalStrut(30));

        //RGB MODEL
        Box boxRGB = Box.createVerticalBox();

        redField = new JTextField("255");
        greenField = new JTextField("255");
        blueField = new JTextField("255");

        redSlider = new JSlider(0, 255, 255);
        greenSlider = new JSlider(0, 255, 255);
        blueSlider = new JSlider(0, 255, 255);

        Box boxRed = Box.createVerticalBox();
        boxRed.add(new JLabel("Red:"));
        boxRed.add(Box.createVerticalStrut(15));
        boxRed.add(redField);
        boxRed.add(Box.createVerticalStrut(15));
        boxRed.add(redSlider);
        boxRGB.add(boxRed);

        Box boxGreen = Box.createVerticalBox();
        boxGreen.add(new JLabel("Green:"));
        boxGreen.add(Box.createVerticalStrut(15));
        boxGreen.add(greenField);
        boxGreen.add(Box.createVerticalStrut(15));
        boxGreen.add(greenSlider);
        boxRGB.add(boxGreen);

        Box boxBlue = Box.createVerticalBox();
        boxBlue.add(new JLabel("Blue:"));
        boxBlue.add(Box.createVerticalStrut(15));
        boxBlue.add(blueField);
        boxBlue.add(Box.createVerticalStrut(15));
        boxBlue.add(blueSlider);
        boxRGB.add(boxBlue);

        currentColorRGB = Color.WHITE;
        colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(200, 200));
        colorPreview.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton button = new JButton("Choose from the palette");
        button.addActionListener(e -> {
            JColorChooser colorChooser = new JColorChooser();
            Color color = colorChooser.showDialog(null, "Choose a color", Color.WHITE);
            if(color != null) {
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                redField.setText(String.valueOf(r));
                greenField.setText(String.valueOf(g));
                blueField.setText(String.valueOf(b));

                redSlider.setValue(r);
                greenSlider.setValue(g);
                blueSlider.setValue(b);

                SelfColor newColor = new SelfColor(r, g, b);
                SelfColor.CMYK newCMYK = newColor.toCMYK();
                SelfColor.HSV newHSV = newColor.toHSV();

                cyanField.setText(String.valueOf((int)newCMYK.c));
                magentaField.setText(String.valueOf((int)newCMYK.m));
                yellowField.setText(String.valueOf((int)newCMYK.y));
                blackField.setText(String.valueOf((int)newCMYK.k));

                cyanSlider.setValue((int)newCMYK.c);
                magentaSlider.setValue((int)newCMYK.m);
                yellowSlider.setValue((int)newCMYK.y);
                blackSlider.setValue((int)newCMYK.k);

                hueField.setText(String.valueOf(newHSV.h));
                saturationField.setText(String.valueOf(newHSV.s));
                valueField.setText(String.valueOf(newHSV.v));

                hueSlider.setValue((int)newHSV.h);
                saturationSlider.setValue((int)newHSV.s);
                valueSlider.setValue((int)newHSV.v);

                currentColorRGB = new Color(r, g, b);
                Color colorNew = getColorRGB();
            }
        });

        boxRGB.add(Box.createVerticalStrut(15));
        boxRGB.add(button);
        boxRGB.add(Box.createVerticalStrut(15));
        modelBox.add(boxRGB);
        modelBox.add(Box.createHorizontalStrut(30));
        add(Box.createVerticalStrut(15));



        //HSV MODEL
        Box boxHSV = Box.createVerticalBox();

        hueField = new JTextField("360");
        saturationField = new JTextField("100");
        valueField = new JTextField("100");

         hueSlider = new JSlider(0, 360, 360);
        saturationSlider = new JSlider(0, 100, 100);
        valueSlider = new JSlider(0, 100, 100);

        Box boxHue = Box.createVerticalBox();
        boxHue.add(new JLabel("Hue:"));
        boxHue.add(Box.createVerticalStrut(15));
        boxHue.add(hueField);
        boxHue.add(Box.createVerticalStrut(15));
        boxHue.add(hueSlider);
        boxHSV.add(boxHue);

        Box boxSaturation = Box.createVerticalBox();
        boxSaturation.add(new JLabel("Saturation:"));
        boxSaturation.add(Box.createVerticalStrut(15));
        boxSaturation.add(saturationField);
        boxSaturation.add(Box.createVerticalStrut(15));
        boxSaturation.add(saturationSlider);
        boxHSV.add(boxSaturation);

        Box boxValue = Box.createVerticalBox();
        boxValue.add(new JLabel("Value:"));
        boxValue.add(Box.createVerticalStrut(15));
        boxValue.add(valueField);
        boxValue.add(Box.createVerticalStrut(15));
        boxValue.add(valueSlider);
        boxHSV.add(boxValue);

        boxHSV.add(Box.createVerticalStrut(15));

        modelBox.add(boxHSV);
        bigVerticalBox.add(modelBox);
        bigVerticalBox.add(Box.createVerticalStrut(15));
        bigVerticalBox.add(colorPreview);
        add(bigVerticalBox);

       addListeners();

    }

    private void updateCoordinatesHSV() {
        float hue = Float.parseFloat(hueField.getText());
        float saturation = Float.parseFloat(saturationField.getText());
        float value = Float.parseFloat(valueField.getText());

        SelfColor newColor = new SelfColor(hue, saturation, value);
        SelfColor.RGB newRGB = newColor.toRGBfromHSV();
        currentColorRGB = new Color(newRGB.r, newRGB.g, newRGB.b);
        SelfColor newColor_1 = new SelfColor(newRGB.r, newRGB.g, newRGB.b);
        SelfColor.CMYK newCMYK = newColor_1.toCMYK();


        hueSlider.setValue((int)hue);
        saturationSlider.setValue((int)saturation);
        valueSlider.setValue((int)value);

        redSlider.setValue(newRGB.r);
        greenSlider.setValue(newRGB.g);
        blueSlider.setValue(newRGB.b);

        redField.setText(String.valueOf(newRGB.r));
        greenField.setText(String.valueOf(newRGB.g));
        blueField.setText(String.valueOf(newRGB.b));

        cyanField.setText(String.valueOf((int)newCMYK.c));
        magentaField.setText(String.valueOf((int)newCMYK.m));
        yellowField.setText(String.valueOf((int)newCMYK.y));
        blackField.setText(String.valueOf((int)newCMYK.k));

        cyanSlider.setValue((int)newCMYK.c);
        magentaSlider.setValue((int)newCMYK.m);
        yellowSlider.setValue((int)newCMYK.y);
        blackSlider.setValue((int)newCMYK.k);
        repaint();

    }
    private void addListeners() {

        //SLIDERS
        redSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSlider();
                Color color = getColorRGB();
            }
        });

        greenSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSlider();
                Color color = getColorRGB();
            }
        });

        blueSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSlider();
                Color color = getColorRGB();
            }
        });

        cyanSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderCMYK();
                Color color = getColorRGB();
            }
        });

        magentaSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderCMYK();
                Color color = getColorRGB();
            }
        });

        yellowSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderCMYK();
                Color color = getColorRGB();
            }
        });

        blackSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderCMYK();
                Color color = getColorRGB();
            }
        });

        hueSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderHSV();
                Color color = getColorRGB();
            }
        });

        saturationSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderHSV();
                Color color = getColorRGB();
            }
        });

        valueSlider.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateSliderHSV();
                Color color = getColorRGB();
            }
        });


        //TEXT FIELDS
        redField.addActionListener(e -> {
            updateCoordinates();
            Color color = getColorRGB();
        });
        greenField.addActionListener(e -> {
            updateCoordinates();
            Color color = getColorRGB();
        });
        blueField.addActionListener(e -> {
            updateCoordinates();
            Color color = getColorRGB();
        });


        cyanField.addActionListener(e -> {
            updateCoordinatesCMYK();
            Color color = getColorRGB();
        });
        magentaField.addActionListener(e -> {
            updateCoordinatesCMYK();
            Color color = getColorRGB();
        });
        yellowField.addActionListener(e -> {
            updateCoordinatesCMYK();
            Color color = getColorRGB();
        });
        blackField.addActionListener(e -> {
            updateCoordinatesCMYK();
            Color color = getColorRGB();
        });


        hueField.addActionListener(e -> {
            updateCoordinatesHSV();
            Color color = getColorRGB();
        });
        saturationField.addActionListener(e -> {
            updateCoordinatesHSV();
            Color color = getColorRGB();
        });
        valueField.addActionListener(e -> {
            updateCoordinatesHSV();
            Color color = getColorRGB();
        });

    }

    private void updateCoordinates() {
        int red = Integer.parseInt(redField.getText());
        int green = Integer.parseInt(greenField.getText());
        int blue = Integer.parseInt(blueField.getText());

        Color color = new Color(red, green, blue);
        currentColorRGB = color;
        SelfColor newColor = new SelfColor(red, green, blue);
        SelfColor.CMYK newCMYK = newColor.toCMYK();
        SelfColor.HSV newHSV = newColor.toHSV();

        redSlider.setValue(red);
        greenSlider.setValue(green);
        blueSlider.setValue(blue);

        cyanField.setText(String.valueOf((int)newCMYK.c));
        magentaField.setText(String.valueOf((int)newCMYK.m));
        yellowField.setText(String.valueOf((int)newCMYK.y));
        blackField.setText(String.valueOf((int)newCMYK.k));

        cyanSlider.setValue((int)newCMYK.c);
        magentaSlider.setValue((int)newCMYK.m);
        yellowSlider.setValue((int)newCMYK.y);
        blackSlider.setValue((int)newCMYK.k);

        hueField.setText(String.valueOf(newHSV.h));
        saturationField.setText(String.valueOf(newHSV.s));
        valueField.setText(String.valueOf(newHSV.v));

        hueSlider.setValue((int)newHSV.h);
        saturationSlider.setValue((int)newHSV.s);
        valueSlider.setValue((int)newHSV.v);

        repaint();
    }
    private void updateSlider() {
        int red = redSlider.getValue();
        int green = greenSlider.getValue();
        int blue = blueSlider.getValue();

        Color color = new Color(red, green, blue);
        currentColorRGB = color;
        SelfColor newColor = new SelfColor(red, green, blue);
        SelfColor.CMYK newCMYK = newColor.toCMYK();
        SelfColor.HSV newHSV = newColor.toHSV();

        redField.setText(String.valueOf(red));
        greenField.setText(String.valueOf(green));
        blueField.setText(String.valueOf(blue));

        cyanField.setText(String.valueOf((int)newCMYK.c));
        magentaField.setText(String.valueOf((int)newCMYK.m));
        yellowField.setText(String.valueOf((int)newCMYK.y));
        blackField.setText(String.valueOf((int)newCMYK.k));

        cyanSlider.setValue((int)newCMYK.c);
        magentaSlider.setValue((int)newCMYK.m);
        yellowSlider.setValue((int)newCMYK.y);
        blackSlider.setValue((int)newCMYK.k);

        hueField.setText(String.valueOf(newHSV.h));
        saturationField.setText(String.valueOf(newHSV.s));
        valueField.setText(String.valueOf(newHSV.v));

        hueSlider.setValue((int)newHSV.h);
        saturationSlider.setValue((int)newHSV.s);
        valueSlider.setValue((int)newHSV.v);

        repaint();
    }



    //CMYK Updates
    private void updateSliderCMYK() {
        float cyan = cyanSlider.getValue();
        float magenta = magentaSlider.getValue();
        float yellow = yellowSlider.getValue();
        float black = blackSlider.getValue();

        SelfColor newColor = new SelfColor(cyan, magenta, yellow, black);
        SelfColor.RGB newRGB = newColor.toRGB();
        currentColorRGB = new Color(newRGB.r, newRGB.g, newRGB.b);
        SelfColor newColor_1 = new SelfColor(newRGB.r, newRGB.g, newRGB.b);
        SelfColor.HSV newHSV = newColor_1.toHSV();

        cyanField.setText(String.valueOf(cyan));
        magentaField.setText(String.valueOf(magenta));
        yellowField.setText(String.valueOf(yellow));
        blackField.setText(String.valueOf(black));

        redSlider.setValue(newRGB.r);
        greenSlider.setValue(newRGB.g);
        blueSlider.setValue(newRGB.b);

        redField.setText(String.valueOf(newRGB.r));
        greenField.setText(String.valueOf(newRGB.g));
        blueField.setText(String.valueOf(newRGB.b));

        hueField.setText(String.valueOf(newHSV.h));
        saturationField.setText(String.valueOf(newHSV.s));
        valueField.setText(String.valueOf(newHSV.v));

        hueSlider.setValue((int)newHSV.h);
        saturationSlider.setValue((int)newHSV.s);
        valueSlider.setValue((int)newHSV.v);
        repaint();
    }
    private void updateCoordinatesCMYK() {
        float cyan = Float.parseFloat(cyanField.getText());
        float magenta = Float.parseFloat(magentaField.getText());
        float yellow = Float.parseFloat(yellowField.getText());
        float black = Float.parseFloat(blackField.getText());

        SelfColor newColor = new SelfColor(cyan, magenta, yellow, black);
        SelfColor.RGB newRGB = newColor.toRGB();
        currentColorRGB = new Color(newRGB.r, newRGB.g, newRGB.b);
        SelfColor newColor_1 = new SelfColor(newRGB.r, newRGB.g, newRGB.b);
        SelfColor.HSV newHSV = newColor_1.toHSV();

        cyanSlider.setValue((int)cyan);
        magentaSlider.setValue((int)magenta);
        yellowSlider.setValue((int)yellow);
        blackSlider.setValue((int)black);

        redSlider.setValue(newRGB.r);
        greenSlider.setValue(newRGB.g);
        blueSlider.setValue(newRGB.b);

        redField.setText(String.valueOf(newRGB.r));
        greenField.setText(String.valueOf(newRGB.g));
        blueField.setText(String.valueOf(newRGB.b));

        hueField.setText(String.valueOf(newHSV.h));
        saturationField.setText(String.valueOf(newHSV.s));
        valueField.setText(String.valueOf(newHSV.v));

        hueSlider.setValue((int)newHSV.h);
        saturationSlider.setValue((int)newHSV.s);
        valueSlider.setValue((int)newHSV.v);
        repaint();

    }

    //HSV Updates
    private void updateSliderHSV() {
        float hue = hueSlider.getValue();
        float saturation = saturationSlider.getValue();
        float value = valueSlider.getValue();

        SelfColor newColor = new SelfColor(hue, saturation, value);
        SelfColor.RGB newRGB = newColor.toRGBfromHSV();
        currentColorRGB = new Color(newRGB.r, newRGB.g, newRGB.b);
        SelfColor newColor_1 = new SelfColor(newRGB.r, newRGB.g, newRGB.b);
        SelfColor.CMYK newCMYK = newColor_1.toCMYK();

        hueField.setText(String.valueOf((int)hue));
        saturationField.setText(String.valueOf((int)saturation));
        valueField.setText(String.valueOf((int)value));

        redSlider.setValue(newRGB.r);
        greenSlider.setValue(newRGB.g);
        blueSlider.setValue(newRGB.b);

        redField.setText(String.valueOf(newRGB.r));
        greenField.setText(String.valueOf(newRGB.g));
        blueField.setText(String.valueOf(newRGB.b));

        cyanField.setText(String.valueOf((int)newCMYK.c));
        magentaField.setText(String.valueOf((int)newCMYK.m));
        yellowField.setText(String.valueOf((int)newCMYK.y));
        blackField.setText(String.valueOf((int)newCMYK.k));

        cyanSlider.setValue((int)newCMYK.c);
        magentaSlider.setValue((int)newCMYK.m);
        yellowSlider.setValue((int)newCMYK.y);
        blackSlider.setValue((int)newCMYK.k);
        repaint();
    }


    //
    // Updating colors
    public Color getColorRGB() {
        colorPreview.setBackground(currentColorRGB);
        return currentColorRGB;
    }

    //Transformation functions
    public class SelfColor {
        private float c, m, y, k;
        private int r, g, b;
        private float h, s, v;
        public SelfColor(float c, float m, float y, float k) {
            this.c = c;
            this.m = m;
            this.y = y;
            this.k = k;
        }
        public SelfColor(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
        public SelfColor(float h, float s, float v) {
            this.h = h;
            this.s = s;
            this.v = v;
        }
        class CMYK {
            double c, m, y, k;
            CMYK(double c, double m, double y, double k) {
                this.c = c;
                this.m = m;
                this.y = y;
                this.k = k;
            }
        }
        class RGB {
            int r, g, b;
            RGB(int r, int g, int b) {
                this.r = r;
                this.g = g;
                this.b = b;
            }
        }
        class HSV {
            float h, s, v;
            HSV(int h, int s, int v) {
                this.h = h;
                this.s = s;
                this.v = v;
            }
        }
        public RGB toRGB() {
            int r = Math.round(255 * (1 - c/100) * (1 - k/100));
            int g = Math.round(255 * (1 - m/100) * (1 - k/100));
            int b = Math.round(255 * (1 - y/100) * (1 - k/100));

            return new RGB(r, g, b);
        }
        public CMYK toCMYK() {
            double key = Math.min(Math.min(1d - r/255d, 1d - g/255d), 1d - b/255d);
            if(1d - key == 0) {
                return new CMYK(0, 0, 0, 100);
            }
            double cyan = (1d - r/255d - key) / (1d - key);
            double magenta = (1d - g/255d - key) / (1d - key);
            double yellow = (1d - b/255d - key) / (1d - key);
            return new CMYK(cyan*100, magenta*100, yellow*100, key*100);
        }
        public HSV toHSV() {
            double max = Math.max(r/255.0, Math.max(g/255.0, b/255.0));
            double min = Math.min(r/255.0, Math.min(g/255.0, b/255.0));
            double delta = max - min;

            double hue, saturation, value = max;

            if(max == 0) {
                saturation = 0;
            } else {
                saturation = delta/max;
            }

            if(delta == 0) {
                hue = 0;
            } else if(max == r/255.0) {
                hue = (g/255.0 - b/255.0)/delta;
            } else if(max == g/255.0) {
                hue = 2 + (b/255.0 - r/255.0)/delta;
            } else {
                hue = 4 + (r/255.0 - g/255.0)/delta;
            }

            hue *= 60;
            if(hue < 0) hue += 360;

            return new HSV((int)hue, (int)(saturation*100), (int)(value*100));
        }
        public RGB toRGBfromHSV() {
            double h_i = Math.floor(h / 60) % 6;
            double v_min = ((100 - s) * v) / 100;
            double a = (v - v_min) * ((h % 60) / 60);
            double v_inc = v_min + a;
            double v_dec = v - a;

            double r = 0, g = 0, b = 0;
            if (h_i == 0) {
                r = v;
                g = v_inc;
                b = v_dec;
            } else if (h_i == 1) {
                r = v_dec;
                g = v;
                b = v_min;
            } else if (h_i == 2) {
                r = v_min;
                g = v;
                b = v_inc;
            } else if (h_i == 3) {
                r = v_min;
                g = v_dec;
                b = v;
            } else if (h_i == 4) {
                r = v_inc;
                g = v_min;
                b = v;
            } else if (h_i == 5) {
                r = v;
                g = v_min;
                b = v_dec;
            }
            int red = (int)(r *= 2.55);
            int green = (int)(g *= 2.55);
            int blue = (int)(b *= 2.55);
            return new RGB(red, green, blue);
        }
    }
}