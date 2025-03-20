package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

import java.util.Objects;


public final class DrawNumberStandardOutputView extends  DrawNumberSwingView implements DrawNumberView {
    public DrawNumberStandardOutputView() {
    }

    @Override
    public void setController(DrawNumberController observer) {
        super.setController(observer);
    }

    @Override
    public void start() {
    System.out.println("Starting CLI output version");
    }

    @Override
    public void result(DrawResult res) {
        try {
            Objects.requireNonNull(res);
        }catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
        System.out.println(res.getDescription());
    }
}