/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.visao.tabulacao;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;

/**
* Classe usada para definir a ordem de tabulação entre os componentes
*/
public class IndexedFocusTraversalPolicy extends FocusTraversalPolicy {

    private ArrayList<Component> components = new ArrayList<>();

    public void addIndexedComponent(Component component) {
         components.add(component);
    }

    //@Override
    @Override
    public Component getComponentAfter(Container aContainer, Component aComponent) {
         int atIndex = components.indexOf(aComponent);
         int nextIndex = (atIndex + 1) % components.size();
         return components.get(nextIndex);
    }

    @Override
    public Component getComponentBefore(Container aContainer, Component aComponent) {
         int atIndex = components.indexOf(aComponent);
         int nextIndex = (atIndex + components.size() - 1) %
                 components.size();
         return components.get(nextIndex);
    }

    @Override
    public Component getFirstComponent(Container aContainer) {
         return components.get(0);
    }

    @Override
    public Component getLastComponent(Container cntnr) {
        return components.get(components.size() - 1);
    }

    @Override
    public Component getDefaultComponent(Container cntnr) {
        return components.get(0);
    }
}
