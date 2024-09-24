package gui.textlabel;

/**
 * TextLabelModel
 * This is the model class of TextLabel
 * @author Simone Cecire
 */
class TextLabelModel {
    private float x, y;
    private int textColor, textSize;
    private TextAlign textAlign;
    private String text;

    /**
     * @param x the x position of the label
     * @param y the y position of the label
     * @param textColor the color of text inside the label
     * @param textSize the color of text inside the label
     * @param textAlign the TextAlign enum value
     * @param text the text inside the label
     * @throws NullPointerException if the text or the reference of TextAlign are null
     */
    TextLabelModel(float x, float y, int textColor, int textSize, TextAlign textAlign, String text) {
        if (textAlign == null) {
            throw new NullPointerException("Parameter text align is null");
        }
        else if (text == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else {
            this.x = x;
            this.y = y;
            this.textColor = textColor;
            this.textSize = textSize;
            this.textAlign = textAlign;
            this.text = text;
        }
    }

    float getX() {
        return x;
    }

    void setX(float x) {
        this.x = x;
    }

    float getY() {
        return y;
    }

    void setY(float y) {
        this.y = y;
    }

    int getTextColor() {
        return textColor;
    }

    void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    int getTextSize() {
        return textSize;
    }

    void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    TextAlign getTextAlign() {
        return textAlign;
    }

    void setTextAlign(TextAlign textAlign) {
        if (textAlign != null) {
            this.textAlign = textAlign;
        }
    }

    String getText() {
        return text;
    }

    void setText(String text) {
        if (text != null) {
            this.text = text;
        }
    }
}
