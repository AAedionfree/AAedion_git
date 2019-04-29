package oonine;

import com.oocourse.specs1.models.Path;
import com.oocourse.specs1.models.PathIdNotFoundException;
import com.oocourse.specs1.models.PathNotFoundException;

public class PathContainer implements com.oocourse.specs1.models.PathContainer {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean containsPath(Path path) {
        return false;
    }

    @Override
    public boolean containsPathId(int pathId) {
        return false;
    }

    @Override
    public Path getPathById(int pathId) throws PathIdNotFoundException {
        return null;
    }

    @Override
    public int getPathId(Path path) throws PathNotFoundException {
        return 0;
    }

    @Override
    public int addPath(Path path) {
        return 0;
    }

    @Override
    public int removePath(Path path) throws PathNotFoundException {
        return 0;
    }

    @Override
    public void removePathById(int pathId) throws PathIdNotFoundException {

    }

    @Override
    public int getDistinctNodeCount() {
        return 0;
    }
}
