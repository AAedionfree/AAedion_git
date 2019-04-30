package oonine;

import com.oocourse.specs1.models.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MyPath implements Path {
    private ArrayList<Integer> nodes = new ArrayList<Integer>();
    private int id;
    private static int count = 0;

    public MyPath(int... nodeList) {
        int i;
        MyPath.count++;
        this.id = count;
        for (i = 0; i < nodeList.length; i++) {
            nodes.add(new Integer(nodeList[i]));
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
        int i = 0;
        int len = 0;
        HashMap map = new HashMap();
        for (i = 0; i < nodes.size(); i++) {
            if (map.get(nodes.get(i)) != null) {
                map.put(nodes.get(i), 1);
                len++;
            }
        }
        return len;
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
        return 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return nodes.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Path) {
            int i;
            if (((MyPath) obj).getNodes().size() != nodes.size()) {
                return false;
            }
            for (i = 0; i < nodes.size(); i++) {
                if (nodes.get(i) != ((MyPath) obj).getNodes().get(i)) {
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

    private int getId(){
        return id;
    }
}
