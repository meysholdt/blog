package blog_001_xtend_and_swt

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display

import static extension blog_001_xtend_and_swt.XtendSWTLib.*
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.layout.FillLayout

class EventHandling {

    def static void main(String[] args) {
        new EventHandling().run(args)
    }
    
    def void run(String[] args) {
        val display = new Display()
        val shell = newShell(display) [ 
            layout = new FillLayout()
            
newButton(SWT::PUSH) [
    text = "Send"
    addListener(SWT::Selection) [
        newMessageBox((widget as Control).shell, SWT::OK) [ 
            message = "Hello World"
        ].open()
    ]
]

        ]
        
        shell.pack()
        shell.open()
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep()
        }
        display.dispose()
    }
}