package com.tonytang.recyclerview.snapper;

public class SelectedPositionStream {

  private int selectedPosition;

  public SelectedPositionStream() {
  }

  public int getSelectedPosition() {
    System.out.println("getSelectedPosition:" + selectedPosition);
    return selectedPosition;
  }

  public void setSelectedPosition(int selectedPosition) {
    System.out.println("setSelectedPosition:" + selectedPosition);
    this.selectedPosition = selectedPosition;
  }

}
