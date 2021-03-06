/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * At most N may be true
 *
 * @author Thom Wiggers
 *
 */
public class AtMostN extends Variable {

    /**
     * cases
     */
    private int n;

    /**
     * variables
     */
    private Variable[] vars;

    /**
     * @param y
     * @param n number of cases to be true at most
     * @param vars variables
     */
    public AtMostN(int y, int n, Variable[] vars) {
        super(y, "AtMost" + n);
        this.n = n;
        this.vars = vars;
    }

    /**
     * at most n may be true of vars
     *
     * @param n
     * @param vars
     */
    public AtMostN(int n, Variable[] vars) {
        super("AtMost" + n);
        this.n = n;
        this.vars = vars;
    }

    /*
     * (non-Javadoc)
     * @see
     * nl.thomwiggers.slpsat.constructs.Variable#addToGateTranslator
     * (org.sat4j.tools.GateTranslator)
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
        translator.addAtMost(lits, this.n);
    }

}
