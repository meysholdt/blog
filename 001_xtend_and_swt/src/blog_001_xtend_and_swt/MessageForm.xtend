package blog_001_xtend_and_swt

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display

import static extension blog_001_xtend_and_swt.XtendSWTLib.*

class MessageForm {

    def static void main(String[] args) {
        new MessageForm().run(args)
    }
    
    def void run(String[] args) {
        val display = new Display()
        val shell = newShell(display) [ 
            setSize(300, 300)
            layout = new GridLayout(3, false)
            newLabel(SWT::NONE) [ 
                text = "To:"
            ]      
            val to = newText(SWT::BORDER) [
                layoutData = newGridData() [
                    grabExcessHorizontalSpace = true
                    horizontalAlignment = SWT::FILL
                ]
            ]
            val send = newButton(SWT::PUSH) [
                text = "Send"
            ]
            val msg = newText(SWT::BORDER.bitwiseOr(SWT::MULTI)) [
                layoutData = newGridData() [
                    grabExcessHorizontalSpace = true
                    grabExcessVerticalSpace = true
                    horizontalSpan = 3
                    horizontalAlignment = SWT::FILL
                    verticalAlignment = SWT::FILL 
                ]
            ]
            send.addListener(SWT::Selection) [
                newMessageBox(send.shell, SWT::OK) [ 
                    message = to.text + "\n" + msg.text
                ].open()
            ]
        ]
        
        shell.open()
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep()
        }
        display.dispose()
    }
}