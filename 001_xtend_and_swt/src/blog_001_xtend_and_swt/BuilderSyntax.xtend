package blog_001_xtend_and_swt

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Display

import static extension blog_001_xtend_and_swt.XtendSWTLib.*

class BuilderSyntax {
    
    def static void main(String[] args) {
        new BuilderSyntax().run(args)
    }
    
    def void run(String[] args) {
        val display = new Display()
        
val shell = newShell(display) [ 
    layout = new GridLayout(3, false)
    newLabel(SWT::NONE) [ 
        text = "To:"
    ]      
    newText(SWT::BORDER) [
        layoutData = newGridData() [
            grabExcessHorizontalSpace = true
            horizontalAlignment = SWT::FILL
        ]
    ]
    newButton(SWT::PUSH) [
        text = "Send"
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