package chat.window

import java.awt.event.ActionEvent
import java.awt.{BorderLayout, Dimension, FlowLayout}
import javax.swing._

import chat.Implicits.Function2ActionListener

class ChatPanel(onSubmit: (ActionEvent) => Any) extends JPanel {

  setLayout(new BorderLayout())

  val listModel = new DefaultListModel[String]()

  val jlist = new JList[String](listModel)
  jlist.setLayoutOrientation(JList.VERTICAL)
  val scroll = new JScrollPane(jlist)
  add(scroll, BorderLayout.CENTER)

  val inputField = new JTextField()
  inputField.setPreferredSize(new Dimension(150, 25))

  val button = new JButton("send")
  button.setPreferredSize(new Dimension(100, 25))

  val southPanel = new JPanel(new FlowLayout())
  add(southPanel, BorderLayout.SOUTH)

  southPanel.add(inputField)
  southPanel.add(button)


  button.addActionListener(onSubmit)
  inputField.addActionListener(onSubmit)

}
