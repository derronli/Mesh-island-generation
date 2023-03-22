package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes;

public interface ChangingBehaviour<T> {
    public T exec(T newTile);
}
