package oonine;

import com.oocourse.specs1.models.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MyPath implements Path {
    private ArrayList<Integer> nodes = new ArrayList<Integer>();
    private HashMap nodehash = new HashMap();
    private int id;
    private static int count = 0;
    private int num = 0;

    public MyPath(int... nodeList) {
        int i;
        for (i = 0; i < nodeList.length; i++) {
            nodes.add(nodeList[i]);
            nodehash.put(nodeList[i], 1);
        }
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public int getNode(int index) {
        return nodes.get(index);
    }

    @Override
    public boolean containsNode(int node) {
        int i = 0;
        for (i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) == node) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getDistinctNodeCount() {
        return nodehash.size();
    }

    @Override
    public boolean isValid() {
        if (nodes.size() >= 2) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Path o) {
        int i = 0;
        for (i = 0; i < Integer.min(o.size(), this.nodes.size()); i++) {
            if (o.getNode(i) != this.nodes.get(i)) {
                return Integer.compare(this.nodes.get(i), o.getNode(i));
            }
        }
        return Integer.compare(this.nodes.size(), o.size());
    }

    @Override
    public Iterator<Integer> iterator() {
        return nodes.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Path) {
            int i;
            if (((Path) obj).size() != nodes.size()) {
                return false;
            }
            for (i = 0; i < nodes.size(); i++) {
                if (nodes.get(i) != ((Path) obj).getNode(i)) {
                    return false;
                }
            }
            return true;
        } else if (obj == null || !(obj instanceof Path)) {
            return false;
        }
        return false;
    }

    private ArrayList<Integer> getNodes() {
        return nodes;
    }

    private int getId() {
        return id;
    }

    public int hashCode() {
        int result = 0;
        int i = 0;
        for (i = 0; i < nodes.size(); i++) {
            if (i != 0) {
                result = 31 * result + nodes.get(i);
            } else {
                result = nodes.get(i);
            }
        }
        return result;
    }
}
