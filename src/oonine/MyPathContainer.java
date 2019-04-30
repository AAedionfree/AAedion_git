package oonine;

import com.oocourse.specs1.models.Path;
import com.oocourse.specs1.models.PathIdNotFoundException;
import com.oocourse.specs1.models.PathNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPathContainer implements com.oocourse.specs1.models.PathContainer {
    private ArrayList<Path> pathlist;
    private HashMap<Path, Integer> pathToInteger;
    private HashMap<Integer, Path> integerToPath;
    private static int num = 0;

    public MyPathContainer() {
        this.pathlist = new ArrayList<>();
        this.pathToInteger = new HashMap<>();
        this.integerToPath = new HashMap<>();
    }

    @Override
    public int size() {
        return pathToInteger.size();
    }

    @Override
    public boolean containsPath(Path path) {
        return pathToInteger.get(path) != null;
    }

    @Override
    public boolean containsPathId(int pathId) {
        return integerToPath.get(pathId) != null;
    }

    @Override
    public Path getPathById(int pathId) throws PathIdNotFoundException {
        if (integerToPath.get(pathId) == null) {
            throw new PathIdNotFoundException(pathId);
        }
        return integerToPath.get(pathId);
    }

    @Override
    public int getPathId(Path path) throws PathNotFoundException {
        if (pathToInteger.get(path) == null) {
            throw new PathNotFoundException(path);
        }
        return pathToInteger.get(path);
    }

    @Override
    public int addPath(Path path) {
        num++;
        pathToInteger.put(path, num);
        integerToPath.put(num, path);
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
