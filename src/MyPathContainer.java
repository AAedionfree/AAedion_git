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
    private HashMap allnode = new HashMap();
    private static int num = 0;

    public MyPathContainer() {
        this.pathlist = new ArrayList<>();
        this.pathToInteger = new HashMap<Path, Integer>();
        this.integerToPath = new HashMap<Integer, Path>();
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
        if (path != null && path.isValid() == true) {
            if(!containsPath(path)){
                num++;
                pathToInteger.put(path, num);
                integerToPath.put(num, path);
                for (Integer integer : path) {
                    if (allnode.get(integer) == null) {
                        allnode.put(integer, 1);
                    }
                }
                return num;
            }else{
                return pathToInteger.get(path);
            }
        } else if (path == null || path.isValid() == false) {
            return 0;
        }
        return 0;
    }

    @Override
    public int removePath(Path path) throws PathNotFoundException {
        if (containsPath(path) == true && path != null && path.isValid() == true) {
            int id = pathToInteger.remove(path);
            integerToPath.remove(id);
            return id;
        } else if (path == null || path.isValid() == false || containsPath(path) == false) {
            throw new PathNotFoundException(path);
        }
        return 0;
    }

    @Override
    public void removePathById(int pathId) throws PathIdNotFoundException {
        if (containsPathId(pathId) == true) {
            Path temp = integerToPath.remove(pathId);
            pathToInteger.remove(temp);
        } else {
            throw new PathIdNotFoundException(pathId);
        }
    }

    @Override
    public int getDistinctNodeCount() {
        return allnode.size();
    }
}
