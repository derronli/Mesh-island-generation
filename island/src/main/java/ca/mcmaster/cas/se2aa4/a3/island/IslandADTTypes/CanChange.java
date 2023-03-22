package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes;

public class CanChange<T> implements ChangingBehaviour<T> {
    @Override
    public T exec(T replacement) {
        return replacement;
    }
}
