/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PERTEMUAN7.Membership.src.view.main;

/**
 *
 * @author adirap
 */

import java.awt.event.*;

public class MainButtonActionListener implements ActionListener {
  private MainFrame mainFrame;

  public MainButtonActionListener(MainFrame mainFrame) {
    this.mainFrame = mainFrame;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == mainFrame.getButtonJenisMember()) {
      mainFrame.showJenisMemberFrame();
    } else {
      mainFrame.showJenisMemberFrame();
    }
  }
}