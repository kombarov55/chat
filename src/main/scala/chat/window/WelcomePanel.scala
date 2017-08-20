package chat.window

import java.awt.event.ActionEvent
import java.awt.{BorderLayout, Dimension}
import javax.swing._

import chat.Implicits.Function2ActionListener

/**
  * Created by nikolaykombarov on 19.08.17.
  */
class WelcomePanel(onSubmit: (ActionEvent) => Any) extends JPanel {

  setLayout(new BorderLayout())


  val nameField = new JTextField("Ваше имя: ")
  nameField.setPreferredSize(new Dimension(200, 10))

  val addressField = new JTextField("Адрес сервера:")
  addressField.setPreferredSize(new Dimension(200, 25))


  val centerPanel = new JPanel()
  val boxLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS)
  centerPanel.setLayout(boxLayout)

  centerPanel.add(nameField)
  centerPanel.add(addressField)


  val button = new JButton("OK")
  button.addActionListener(onSubmit)
  nameField.addActionListener(onSubmit)
  addressField.addActionListener(onSubmit)


  add(centerPanel, BorderLayout.CENTER)
  add(button, BorderLayout.SOUTH)
  Window.pack()

}
