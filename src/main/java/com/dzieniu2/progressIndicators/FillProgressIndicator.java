
package com.dzieniu2.progressIndicators;

import javafx.scene.control.Skin;
public class FillProgressIndicator extends ProgressCircleIndicator {

    public FillProgressIndicator() {
        this.getStylesheets().add(RingProgressIndicator.class.getResource("/css/fillprogress.css").toExternalForm());
        this.getStyleClass().add("fillindicator");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new FillProgressIndicatorSkin(this);
    }
}
