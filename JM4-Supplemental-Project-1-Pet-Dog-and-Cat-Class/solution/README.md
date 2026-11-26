# Pet Sounds

All pets have a name. Every subtype supplies its own sound. The driver can call the sound method through a Pet reference without checking the subtype.

1. Trace the parent constructor and the inherited `introduce` method. That method calls `sound` on the same object.
2. Complete the Cat override. Keep Cat's jump distance and Dog's service status in the respective subclasses.
3. Predict the output for two Pet references. A Cat must meow even when the variable type is Pet.

Save the IDE workspace, download its ZIP, and extract the folder. Use Java 21 in that folder.

```sh
javac --release 21 -Xlint:all -Werror *.java
java Main
java ContractTests
```

The starter compiles. Its checks show which behavior still needs to be implemented.
