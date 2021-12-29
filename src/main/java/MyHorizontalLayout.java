/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 *
 * @author ACER
 */
public class MyHorizontalLayout extends HorizontalLayout {

    public static String CSS_COLOR = "green";

    public MyHorizontalLayout(Component... children) {
        super(children);
        if (ConfigGenerale.ENCADRE_LAYOUT) {
            Paragraph nom = new Paragraph(this.getClass().getSimpleName());
            nom.getStyle().set("font-family", "Monospace")
                    .set("font-size", "0.8em").set("color", CSS_COLOR)
                    .set("font-style", "italic");
            this.addComponentAsFirst(nom);
            this.getStyle().set("border", "1px dotted "+CSS_COLOR);

        }
    }
    
}
