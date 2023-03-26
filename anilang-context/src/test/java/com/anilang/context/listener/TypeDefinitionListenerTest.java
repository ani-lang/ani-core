/*
 * Property of Opencore
 */

package com.anilang.context.listener;

import com.anilang.context.AniContext;
import com.anilang.context.ExampleFile;
import com.anilang.context.Type;
import com.anilang.context.impl.BaseAniContext;
import com.anilang.parser.AniFile;
import com.anilang.parser.antlr.AniParser;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests file.
 *
 * @since 0.7.0
 */
@SuppressWarnings("PMD.TooManyMethods")
class TypeDefinitionListenerTest {

    // @checkstyle JavadocMethodCheck (500 lines)
    @Test
    void classTypeDefinition() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-definition/class_definition.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            context.get("1-0").getType(),
            Type.CLASS
        );
    }

    @Test
    void struct() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-definition/struct_definition.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            context.get("1-0").getType(),
            Type.STRUCT
        );
    }

    @Test
    void function() throws IOException {
        final AniParser parser = new AniFile(
            new ExampleFile("type-definition/func_definition.ani").inputStream()
        ).parse();
        final AniContext context = new BaseAniContext();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierDeclarationListener(context),
            parser.file()
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new IdentifierUsageListener(context),
            parser.file()
        );
        parser.reset();
        Assertions.assertDoesNotThrow(
            () -> ParseTreeWalker.DEFAULT.walk(
                new IdentifierValidationListener(context),
                parser.file()
            )
        );
        parser.reset();
        ParseTreeWalker.DEFAULT.walk(
            new TypeDefinitionListener(context),
            parser.file()
        );
        Assertions.assertEquals(
            context.get("1-0").getType(),
            Type.FUNCTION
        );
    }
}
