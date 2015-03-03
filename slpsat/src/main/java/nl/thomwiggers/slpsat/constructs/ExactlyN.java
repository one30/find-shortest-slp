/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * @author Thom Wiggers
 *
 */
public class ExactlyN extends Variable {

    private int n;
    private Variable[] vars;

    /**
     * @param y
     */
    public ExactlyN(int y, int n, Variable[] vars) {
        super(y, "exactly" + n);
        this.n = n;
        this.vars = vars;
    }

    /**
     *
     */
    public ExactlyN(int n, Variable[] vars) {
        super("exactly" + n);
        assert n == 1;
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
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.added)
            return;
        this.added = true;

        VecInt lits = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            lits.push(var.getIndex());
        }
        vars = null;
        translator.addExactly(lits, this.n);
    }

}
