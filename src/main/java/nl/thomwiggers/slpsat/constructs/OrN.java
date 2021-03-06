/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * Or of vars (p1 v p2 v p3 ...)
 *
 * @author Thom Wiggers
 *
 */
public class OrN extends Variable {

    protected Variable[] vars;

    /**
     * Or of vars
     *
     * @param y index
     * @param vars the variables
     */
    public OrN(int y, Variable[] vars) {
        super(y, "OrN");
        this.vars = vars;
    }

    /**
     * Or of vars
     *
     * @param vars
     */
    public OrN(Variable[] vars) {
        super("OrN");
        this.vars = vars;
    }

    /*
     * (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#
     * addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;

        VecInt lits = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            lits.push(var.getIndex());
        }
        this.vars = null;
        translator.or(this.getIndex(), lits);

    }

}
