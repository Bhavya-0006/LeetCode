class Solution {
    class Node {
        char left, right;
        int prefix, suffix, best, len;
        Node() {}
        Node(char c) {
            left = right = c;
            prefix = suffix = best = len = 1;
        }
    }
    Node[] tree;
    String s;
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s;
        int n = s.length();
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int q = queryIndices.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);
            update(1, 0, n - 1, index, c);
            ans[i] = tree[1].best;
        }
        return ans;
    }
    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }
        int mid = l + (r - l) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    Node merge(Node a, Node b) {
        Node res = new Node();
        res.len = a.len + b.len;
        res.left = a.left;
        res.right = b.right;
        res.prefix = a.prefix;
        res.suffix = b.suffix;
        res.best = Math.max(a.best, b.best);
        if (a.right == b.left) {
            if (a.prefix == a.len) {
                res.prefix = a.len + b.prefix;
            }
            if (b.suffix == b.len) {
                res.suffix = b.len + a.suffix;
            }
            res.best = Math.max(
                res.best,
                a.suffix + b.prefix
            );
        }
        return res;
    }
    void update(int node, int l, int r, int index, char c) {
        if (l == r) {
            tree[node] = new Node(c);
            return;
        }
        int mid = l + (r - l) / 2;
        if (index <= mid) {
            update(node * 2, l, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, r, index, c);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
}