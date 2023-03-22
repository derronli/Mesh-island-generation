package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes;

public interface ChangingBehaviour<T> {
    T exec(T newTile);
}
