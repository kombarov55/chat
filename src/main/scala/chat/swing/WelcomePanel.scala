package chat.swing

import java.awt.event.ActionEvent
import java.awt.{BorderLayout, Dimension}
import javax.swing._

import chat.Implicits.Function2ActionListener

/**
  * Created by nikolaykombarov on 19.08.17.
  */
class WelcomePanel(onSubmit: (ActionEvent) => Any) extends JPanel {

  setLayout(new BorderLayout())

  val nameLabel = new JLabel("Ваше имя: ")
  val nameField = new JTextField()

  val addressLabel = new JLabel("Адрес сервера: ")
  val addressField = new JTextField()

  val centerPanel = new JPanel()
  val boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS)
  centerPanel.setLayout(boxLayout)

  for (component <- List(nameLabel, nameField, addressLabel, addressField)) {
    centerPanel.add(component)
  }

  val button = new JButton("OK")
  button.addActionListener(onSubmit)
  nameField.addActionListener(onSubmit)
  addressField.addActionListener(onSubmit)


  add(centerPanel, BorderLayout.CENTER)
  add(button, BorderLayout.SOUTH)

}
