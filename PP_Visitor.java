import syntaxtree.*;

/* 
 * this class uses the Visitor pattern 
 * to pretty print the miniC program
 * The idea is to have each node return its string representation!
*/

 public class PP_Visitor implements Visitor
 {
 
   private String indentString(int indent) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4*indent; ++i) {
        sb.append(' ');
        }
        return sb.toString();
   }

   public Object visit(Program node, Object data){
     int indent = (int)data;
     String mainClass = (String) node.m.accept(this,indent);
     String classDeclList = "";
     if (node.c!=null) {
          classDeclList = (String) node.c.accept(this,indent);
     }

     return indentString(indent)+mainClass+"\n"+classDeclList;
   }

   public Object visit(MainClass node, Object data){
     int indent = (int)data;
     String id = (String) node.i.accept(this,0);
     String statements = (String) node.s.accept(this,indent+2);
     return indentString(indent)+"class "+id+" {\n"+
     indentString(indent+1)+"public static void main(String[] args) {\n"+
     statements+
     indentString(indent+1)+"}\n"+
     indentString(indent)+"}\n";

   }

   public Object visit(ClassDecl node, Object data){
     int indent = (int)data;
     String id = (String) node.i.accept(this,0);

     String varList = "";
     if (node.v!=null){
         varList = (String) node.v.accept(this,indent+1);
     }

     String methodList = "";
     if (node.m!=null){
         methodList = (String) node.m.accept(this,indent+1);
     }

     return indentString(indent)+"class "+id+" {\n"+
     varList+methodList+
     indentString(indent)+"}\n";
   }

   public Object visit(VarDecl node, Object data){
        int indent = (int) data; 
        String t = (String) node.t.accept(this,indent);
        String i = (String) node.i.accept(this,indent);
        return indentString(indent)+t + " "+i +";\n";
   }

   public Object visit(MethodDecl node, Object data){
        int indent = (int) data; 
        String type = (String) node.t.accept(this,indent);
        String id = (String) node.i.accept(this,0);
        String formalList = "";
        if (node.f!=null){
            formalList = (String) node.f.accept(this,0);
        }
        String varList = "";
        if (node.v!=null){
            varList = (String) node.v.accept(this,indent+1);
        }
        String statementList = "";
        if (node.s!=null){
            statementList = (String) node.s.accept(this,indent+1);
        }
        String expr = (String) node.e.accept(this,0);
        return 
        indentString(indent)+
        type+" "+id+"("+formalList+"){\n"+
        varList +
        statementList+
        indentString(indent+1)+"return "+expr +";\n"+
        indentString(indent)+"}\n";
   }

   public Object visit(Formal node, Object data){
        String type = (String) node.t.accept(this,0);
        String id = (String) node.i.accept(this,0);
        return type+" "+id;
   }

   public Object visit(IntArrayType node, Object data){
     return "int[]";
   }
   public Object visit(IntegerType node, Object data){
        return "int";
   }
   public Object visit(BooleanType node, Object data){
        return "bool ";
   }
   public Object visit(IdentifierType node, Object data){
     return node.s;
   }
   public Object visit(Block node, Object data){
        int indent = (int) data; 
        String result="";
        if (node.slist!=null){
            result = (String) node.slist.accept(this,indent+1);
        }
        
        return indentString(indent)+"{\n"+
               result+
               indentString(indent)+"}\n";
   }
   public Object visit(If node, Object data){
        int indent = (int) data; 
        String expr = (String)node.e.accept(this,0);
        String s1 = (String) node.s1.accept(this,indent+1);
        String s2 = (String) node.s2.accept(this,indent+1);
        return indentString(indent)+
        "if ("+expr+")\n"+
        s1+
        indentString(indent)+"else\n"+
        s2;
   }
   public Object visit(While node, Object data){
     int indent = (int) data;
     String expr = (String) node.e.accept(this,0);
     String s = (String) node.s.accept(this,indent+1);
     return indentString(indent)+"while ("+expr+")\n"+s;
   }

   public Object visit(Print node, Object data){
        // 
        int indent = (int) data; 
        String e = (String) node.e.accept(this,indent);
        //--indent;
        return indentString(indent) + "System.out.println("+e+");\n";
   }

   public Object visit(Assign node, Object data){
        // 
        int indent = (int) data; 
        String i = (String) node.i.accept(this,indent);
        String e = (String) node.e.accept(this,indent);
        //--indent;
        return indentString(indent)+i+" = "+e+";\n";
   }
   
   public Object visit(ArrayAssign node, Object data){ 
     int indent = (int) data;
     Identifier i = node.i;
     Exp e1 = node.e1;
     Exp e2 = node.e2;
     String id = (String) i.accept(this,0);
     String exp1 = (String) e1.accept(this,0);
     String exp2 = (String) e2.accept(this,0);
     data = indentString(indent)+id+"["+exp1+"] = "+exp2+";\n";
     return data; 
   } 

   public Object visit(And node, Object data){
     String e1 = (String) node.e1.accept(this,0);
     String e2 = (String) node.e2.accept(this,0);
     return e1+" && "+e2;
   }

   public Object visit(LessThan node, Object data){
        // 
        int indent = (int) data; 
        String e1 = (String) node.e1.accept(this,indent);
        String e2 = (String) node.e2.accept(this,indent);
        return e1+"<"+e2;
   }
   public Object visit(Plus node, Object data){
        // 
        int indent = (int) data; 
        String e1 = (String) node.e1.accept(this,indent);
        String e2 = (String) node.e2.accept(this,indent);
        return e1+" + "+e2;
   }
   public Object visit(Minus node, Object data){
        // 
        int indent = (int) data; 
        String e1 = (String) node.e1.accept(this,indent);
        String e2 = (String) node.e2.accept(this,indent);
        return e1+" - "+e2;
   }
   public Object visit(Times node, Object data){
        // 
        int indent = (int) data; 
        String e1 = (String) node.e1.accept(this,indent);
        String e2 = (String) node.e2.accept(this,indent);
        return e1+" * "+e2;
   }
   public Object visit(ArrayLookup node, Object data){
        int indent = (int) data;
        String i = (String) node.e1.accept(this,indent);
        String e = (String) node.e2.accept(this,0);
        return i+"["+e+"]";
   }
   public Object visit(ArrayLength node, Object data){
        String e = (String) node.e.accept(this,0);
        return e+".length";
   }
   public Object visit(Call node, Object data){
        // 
        int indent = (int) data; 
        String e1 = (String) node.e1.accept(this,indent);
        String i = (String) node.i.accept(this,indent);
        String e2 = (String) node.e2.accept(this,indent);
        //class.method(args)
        return e1+"."+i+"("+e2+")";
   }

   public Object visit(IntegerLiteral node, Object data){
        int i = node.i;
        return ""+i;
   }

   public Object visit(True node, Object data){ 
        //--indent;
        return "true";
   }
   public Object visit(False node, Object data){
        //--indent;
        return "false";
   }
   public Object visit(IdentifierExp node, Object data){
        String s = node.s;
        return s;
   }
   public Object visit(This node, Object data){
     return "this";
   }
   public Object visit(NewArray node, Object data){
     String e = (String) node.e.accept(this, 0);
     return "new int["+e+"]";
   }
   public Object visit(NewObject node, Object data){
     String id = (String) node.i.accept(this, 0);
     return "new "+id+"()";
   }

   public Object visit(Not node, Object data){
     String e = (String) node.e.accept(this, 0);
     return "!"+e;
   }
   public Object visit(Identifier node, Object data){
        String s = node.s;
        return s;
   }

   public Object visit(ExpGroup node, Object data){
     String e="";
     if (node.e!=null){
         e = (String) node.e.accept(this,data);
     }
     return "("+e+")";
   }

   public Object visit(ClassDeclList node, Object data){
     String classDecl = (String) node.c.accept(this,0);
     if (node.clist!=null) {
          String classDeclList = (String) node.clist.accept(this,0);
          classDecl = classDeclList+"\n"+classDecl;
     }
     return classDecl;
   }

   public Object visit(ExpList node, Object data){
        String e = (String) node.e.accept(this,0);
        if (node.elist!=null){
            String e1 = (String) node.elist.accept(this,0);
            e = e1 + ","+e;
        }

        return e;
   }

   public Object visit(FormalList node, Object data){
        // 
        String f = (String) node.f.accept(this,0);
        if (node.flist!=null){
            String f1 = (String) node.flist.accept(this,0);
            f = f1 +","+f;
        }
        //--indent;
        return f;
   }
   public Object visit(MethodDeclList node, Object data){
        //  
        int indent = (int) data; 
        String m = (String) node.m.accept(this,indent);
        if (node.mlist!=null){
            String m2 = (String) node.mlist.accept(this,indent);
            m = m2 +"\n"+m; 
        }
        return m;
   }
   public Object visit(StatementList node, Object data){
     int indent = (int) data; 
     String statements = (String) node.s.accept(this, indent);
     if (node.slist!=null){
         String slist = (String) node.slist.accept(this,indent);
         statements = slist+statements;
     }
     return statements;
   }
   public Object visit(VarDeclList node, Object data){
        int indent = (int) data; 
        String v = (String) node.v.accept(this,indent);        
        if (node.vlist!=null){
            String vlist = (String) node.vlist.accept(this,indent);
            v = vlist + v;
        }
        return v;
   }
}

 

 