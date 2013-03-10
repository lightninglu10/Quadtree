public void add(Point p) {
        Node cur, parent, index;
        index = new Node(p);
        if (tsize.size() <= _transitionSize) {
            tsize.add(p);
            if (root == null) {
                root = tsize.get(0);
            }
        } else {
            if (root == null) {
                root = tsize.get(1);
            } else {
                cur = root;
                while (true) {
                    Parent = cur;
                    if (getView().getX(p) > getView().getX(cur.getpoint())
                        && getView().getY(p) > getView().getY(cur.getpoint())) {
                        cur = cur.getQ1();
                        if (cur == null) {
                            Parent.changeQ1(index);
                            points.add(index);
                            return;
                        }
                    } else if (getView().getX(p)
                               > getView().getX(cur.getpoint())
                               && getView().getY(p)
                               < getView().getY(cur.getpoint())) {
                        curr = curr.getQ4();
                        if (curr == null) {
                            Parent.changeQ1(index);
                            points.add(index);
                            return;
                        }
                    } else if (getView().getX(p)
                               < getView().getX(cur.getpoint())
                               && getView().getY(p)
                               > getView().getY(cur.getpoint())) {
                        cur = cur.getQ2();
                        if (cur == null) {
                            Parent.changeQ2(index);
                            points.add(index);
                            return;
                        }
                    } else if (getView().getX(p)
                               < getView().getx(cur.getpoint())
                               && getView().getY(p)
                               < getView().getY(cur.getpoint())) {
                        cur = cur.getQ3();
                        if (cur == null) {
                            Parent.changeQ2(index);
                            points.add(index);
                            return;
                        }
                    }
                }
            }
        }
    }
